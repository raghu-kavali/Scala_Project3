package mcit.scala.project

import java.io.{BufferedWriter, FileWriter}

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Enricher extends App {
  def tripList: List[Trip] = {
    val source = Source
      .fromFile("/root/Downloads/ScalaGTFSProject/trips.txt")
    val input = source.getLines().drop(1)
      .toList
      .tail
      .map(_.split(",", -1))
      .map(p => Trip(p(0), p(1), p(2), p(3), p(4), p(5), p(6),
        if (p(7).isEmpty) None else Some(p(7)),
        if (p(8).isEmpty) None else Some(p(8))))
    source.close()
    input
  }

  def routeList: List[Route] = {
    val source = Source
      .fromFile("/root/Downloads/ScalaGTFSProject/routes.txt")
    val input = source.getLines().drop(1)
      .toList
      .tail
      .map(_.split(",", -1))
      .map(p => Route(p(0), p(1), p(2), p(3), p(4), p(5), p(6), p(7)))
    source.close()
    input
  }

  val routeTrip: List[RouteTrip] = new MapJoin().join(tripList, routeList)

  def calendar: List[Calendar] = {
    val source = Source
      .fromFile("/root/Downloads/ScalaGTFSProject/calendar.txt")
    val input = source.getLines().drop(1)
      .toList
      .tail
      .map(_.split(",", -1))
      .map(c => Calendar(c(0), c(1), c(2), c(3), c(4), c(5), c(6), c(7), c(8), c(9)))
    source.close()
    input
  }

  val enrichedTrip: List[EnrichedTrip] = new GenericNestedLoopJoin().join(routeTrip, calendar).filter(a => a.calender.get.monday.equals("1") && a.routeTrip.route.get.route_type.equals("1"))

  val Output = new BufferedWriter(new FileWriter("/root/Downloads/ScalaProjectOutput/Output.csv"))
  var listOfRecords = new ListBuffer[Array[String]]()
  val header: String = "Route Id, Service Id, Trip Id, Trip Head Sign, Direction Id,Shape Id, Wheelchair accessible, Note_FR, Note En, Agency Id,Route Short Name, Route Long Name, Route Type, Route Url, Route Colour,Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday,tart Date, End Date"
  Output.write(header)
  for (line <- enrichedTrip) {
    Output.newLine()
    Output.write(line.toString)
  }
  Output.close()
}



