(ns mistake.test.template
  (:use [mistake render enlive])
  (:use [clojure.test])
  (:use [mistake.test.fixtures])
  (:require [net.cgrand.enlive-html :as h]))

(deftest enlive-template-test
  (let [html (h/html-resource (string-input-stream index-page))
        test-t (enlive-template {:content html
                                 :meta {:enlive {:.a {:set-attr {:height :h}}
                                                 :.b {:html-content :bread}
                                                 :title {:content :title}
                                                 (keyword ".b a") {:donthave :hee}}}})]
    (is (= (test-t {:title "Good Title" :h "100px" :bread "<a></a>" :hee "h"}) result-page))))

(deftest mustache-template-test
  (let [html "Hello {{name}}"
        test-t (mustache-template {:content html :meta {}})]
    (is (= (test-t {:name "Peter Chow"}) "Hello Peter Chow"))))