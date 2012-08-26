(ns mistake.test.article
  (:use [mistake.features article])
  (:use [clojure.test])
  (:use [mistake.test.fixtures]))


(deftest should-return-valid-id
  "change title to valid filename which can also be used as uri"
  (is (= "i-am-the-king-of-the-road" (title-id "I am the King of the Road")))
  (is (= "" (title-id "")))
  (is (= nil (title-id nil))))