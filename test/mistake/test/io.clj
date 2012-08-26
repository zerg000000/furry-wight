(ns mistake.test.io
  (:use [mistake io]
        [clojure.test]))

(deftest doc-test
  "doc is content (+ meta data), unique in file system"
  (is (= (doc-map
           {"john.html" {:f "f"} "john.html.json" {:h "f"}})
        {"john.html" {:meta {:h "f" :filename "john.html"} :content {:f "f"}}}))
  (is (= (doc-map {"john.html" {:g "g"}})
        {"john.html" {:meta {:filename "john.html"} :content {:g "g"}}}))
  (is (= (doc-map {"john.html.json" {:g "g"}})
        {})))