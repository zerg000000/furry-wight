(ns mistake.render
  (:require [net.cgrand.enlive-html :as h]
            [mistake.enlive :as e]))

(defn doc-template [^String name doc]
  (cond
    (.endsWith name ".enlive.html") (e/enlive-template doc)))