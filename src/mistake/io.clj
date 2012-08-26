(ns mistake.io
  (:require [net.cgrand.enlive-html :as h]
            [cheshire.core :as json]
            [clj-time.format :as tformat])
  (:import (org.pegdown PegDownProcessor Extensions)))

(defn read-file [^java.io.File f]
  (let [^String name (.getPath f)]
    (cond
      (.endsWith name ".html") [name (h/html-resource f)]
      (.endsWith name ".json") [name (json/decode (slurp f) false)]
      (.endsWith name ".md") [name (.markdownToHtml (PegDownProcessor. (Extensions/ALL)) (slurp f))]
      (.endsWith name ".mustache") [name (slurp f)]
      :else nil
      )))

(defn file-map [dir]
  (into {}
    (pmap read-file
      (remove #(.isDirectory %) (file-seq (clojure.java.io/file dir))))))

(defn doc-map [files]
  (into {}
    (pmap (fn [[^String key value]]
            [key {:meta (merge {:filename key
                                :id ""}
                          (get files (str key ".json")))
                  :content value}]) (remove #(.endsWith (first %) ".json") files))))

(defn doc-seq [dir]
  (-> dir file-map doc-map))
