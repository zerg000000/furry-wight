(ns mistake.io
  (:require [net.cgrand.enlive-html :as h]
            [cheshire.core :as json])
  (:import (org.pegdown PegDownProcessor Extensions)))

(defn read-file [^java.io.File f]
  (let [^String name (.getName f)]
    (cond
      (.endsWith name ".html") [name (h/html-resource f)]
      (.endsWith name ".json") [name (json/decode (slurp f) true)]
      (.endsWith name ".md") [name (.markdownToHtml (PegDownProcessor. (Extensions/ALL)) (slurp f))]
      :else nil
      )))

(defn file-map [dir & recursive]
  (into {}
    (filter identity
      (map (fn [^java.io.File f]
             (if (not (.isDirectory f))
               (read-file f)))
        (file-seq (clojure.java.io/file dir))))))

(defn doc-map [files]
  (into {}
    (filter identity
      (map (fn [[^String key value]]
             (let [meta-file (str key ".json")
                   doc-file (get (.split key ".json") 0)]
               (cond
                 (get files meta-file) [key {:meta (get files meta-file) :content value}]
                 (and (.endsWith key ".json")
                   (get files doc-file)) nil
                 :else [key value]))) files)
      )))