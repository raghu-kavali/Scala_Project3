package mcit.scala.project

case class Route(
                  route_id: String,
                  agency_id: String,
                  route_short_name: String,
                  route_long_name: String,
                  route_type: String,
                  route_url: String,
                  route_color: String,
                  route_text_color: String
                )

object Route {
  def toCsv(route: Route): String = {
    route.route_id + "," +
      route.agency_id + "," +
      route.route_short_name + "," +
      route.route_long_name + "," +
      route.route_type + "," +
      route.route_url + "," +
      route.route_color + "," +
      route.route_text_color

  }
}

case class RouteTrip(trip: Trip, route: Option[Route]) {
}

case class EnrichedTrip(routeTrip: RouteTrip, calender: Option[Calendar]) {}

