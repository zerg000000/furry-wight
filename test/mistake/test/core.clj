(ns mistake.test.core
  (:use [mistake core io render])
  (:use [clojure.test])
  (:require [net.cgrand.enlive-html :as h]))

(deftest doc-test
 "doc is content (+ meta data), unique in file system"
  (is (= (doc-map
           {"john.html" {:f "f"} "john.html.json" {:h "f"}})
          {"john.html" {:meta {:h "f"} :content {:f "f"}}}))
  (is (= (doc-map {"john.html" {:g "g"}})
         {"john.html" {:g "g"}})))

(deftest enlive-template-test
  (let [test-t (enlive-template {:content (h/html-resource (java.io.File. "templates/test.html"))
                                 :meta {:body ["content" "content"]
                                        :title ["content" "title"]}})]
  (is (= (test-t {:title "Good Title"}) "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n    \"http://www.w3.org/TR/html4/loose.dtd\">\n<html>\n<head>\n  <title>Good Title</title>\n</head>\n<body></body>\n</html>"))))