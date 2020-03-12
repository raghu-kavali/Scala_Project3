package mcit.scala.project

case class Calendar(
                     service_id: String,
                     monday: String,
                     tuesday: String,
                     wednesday: String,
                     thursday: String,
                     friday: String,
                     saturday: String,
                     sunday: String,
                     start_date: String,
                     end_date: String
                   )

object Calendar {
  def toCsv(calendar: Calendar): String = {
    calendar.service_id + "," +
      calendar.monday + "," +
      calendar.tuesday + "," +
      calendar.wednesday + "," +
      calendar.thursday + "," +
      calendar.friday + "," +
      calendar.saturday + "," +
      calendar.sunday + "," +
      calendar.start_date + "," +
      calendar.end_date
  }
}