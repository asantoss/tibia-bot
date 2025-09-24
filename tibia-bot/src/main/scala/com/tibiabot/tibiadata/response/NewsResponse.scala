package com.tibiabot.tibiadata.response

import java.time.LocalDate

case class NewsEntry(
  id: Int,
  date: String,  // Format: "YYYY-MM-DD"
  news: String,  // This is the title/description
  category: String,
  `type`: String,
  url: String,
  url_api: Option[String] = None  // Optional as it might not always be present
)

case class NewsData(
  news: List[NewsEntry]
)

case class NewsResponse(
  information: Information,
  news: NewsData
)

// News ticker structures - Now using same structure as regular news
case class NewsTickerEntry(
  id: Int,
  date: String,  // Format: "YYYY-MM-DD"
  news: String,  // This is the ticker message
  category: String,
  `type`: String,
  url: String,
  url_api: String
)

case class NewsTickerData(
  news: List[NewsTickerEntry]  // Changed from newstickers to news
)

case class NewsTickerResponse(
  information: Information,
  news: NewsTickerData  // Changed from newstickers to news
)