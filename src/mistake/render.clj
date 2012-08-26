(ns mistake.render
  (:require [net.cgrand.enlive-html :as h]
            [mistake.enlive :as e]
            [clostache.parser :as m]))

(defn mustache-template [doc]
  (fn [data] (m/render (:content doc) (merge (:meta doc) data)))
  )

(defn doc-template [^String name doc]
  (cond
    (.endsWith name ".html") (e/enlive-template doc)
    (.endsWith name ".mustache") (mustache-template doc)
    :else (fn [data] doc)))