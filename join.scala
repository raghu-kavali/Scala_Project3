package mcit.scala.project
trait Join[L, R, Q] {
  def join(a: List[L], b: List[R]): List[Q]
}

case class JoinOutput(left: Any, right: Option[Any])

class GenericNestedLoopJoin extends Join[RouteTrip, Calendar, EnrichedTrip] {
  override def join(a: List[RouteTrip], b: List[Calendar]): List[EnrichedTrip] =
    for {
    routeTrip <- a
    calendar <- b
    if routeTrip.trip.service_id == calendar.service_id
  }

   yield EnrichedTrip(routeTrip, Some(calendar))
}
class MapJoin extends Join[Trip, Route, RouteTrip] {

  override def join(a: List[Trip], b: List[Route]): List[RouteTrip] = {
    val t: Map[String, Route] = b.map(route => route.route_id -> route).toMap
    a.filter(trip => t.contains(trip.route_id)).map(trip => RouteTrip(trip, Some(t(trip.route_id))))
  }
}
