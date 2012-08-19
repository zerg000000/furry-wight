(ns mistake.test.core
  (:use [mistake core io render enlive])
  (:use [clojure.test])
  (:use [mistake.test.fixtures])
  (:require [net.cgrand.enlive-html :as h]))

(deftest doc-test
 "doc is content (+ meta data), unique in file system"
  (is (= (doc-map
           {"john.html" {:f "f"} "john.html.json" {:h "f"}})
          {"john.html" {:meta {:h "f"} :content {:f "f"}}}))
  (is (= (doc-map {"john.html" {:g "g"}})
         {"john.html" {:g "g"}})))

(deftest enlive-template-test
  (let [ html (h/html-resource (string-input-stream index-page))
         test-t (enlive-template html
                                 {"body" {"content" "content"}
                                        "title" {"content" "title"}})]
  (is (= (test-t {"title" "Good Title"}) result-page))))