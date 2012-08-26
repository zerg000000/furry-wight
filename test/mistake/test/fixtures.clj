(ns mistake.test.fixtures)

(defn string-input-stream [^String s]
  "Returns a ByteArrayInputStream for the given String."
  (java.io.ByteArrayInputStream. (.getBytes s)))

(def index-page "<!DOCTYPE html>\n<html>\n<head>\n  <title>Bad Title</title>\n</head>\n<body><div class='a'/><div class='b' /></body>\n</html>")

(def result-page "<!DOCTYPE html>\n<html>\n<head>\n  <title>Good Title</title>\n</head>\n<body><div height=\"100px\" class=\"a\"></div><div class=\"b\"><a>h</a></div></body>\n</html>")
