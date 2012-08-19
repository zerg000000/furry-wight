(ns mistake.enlive
  (:require [net.cgrand.enlive-html :as h]))

(defmacro enlive-str-template [source args forms]
  "transform (data)=>(nodes) to (data)=>(str)"
  `(comp (partial apply str) h/emit* (fn ~args (h/at* ~source ~forms))))

(defn set-attr
  "Assocs attributes on the selected element."
  [kvs]
  #(assoc %  :attrs (assoc (:attrs % {}) kvs)))

(defn to-selector [^String s]
  "Convert string to a vector of selector identity."
  (vec (map (fn [k] (keyword k)) (.split s " "))))

(defn to-form [rule d]
   (cond
     (= rule "content")      (h/content d)
     (= rule "html-content") (h/html-content d)
     (= rule "set-attr")     (apply h/set-attr d)
     :else (h/content d)))

(defn enlive-template [content meta]
  (enlive-str-template content [data]
    (map
      (fn [[selector rules]]
        [(to-selector selector)
         (apply h/do->
           (map
             (fn [[k v]] (to-form k (get data v))) rules))]) meta)))