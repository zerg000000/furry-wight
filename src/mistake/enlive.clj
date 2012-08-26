(ns mistake.enlive
  (:require [net.cgrand.enlive-html :as h]))

(defmacro enlive-str-template [source args forms]
  "transform (data)=>(nodes) to (data)=>(str)"
  `(comp (partial apply str) h/emit* (fn ~args (h/at* ~source ~forms))))

(defn keywords-vector [kw]
  "Convert keyword/string to a vector of keywords."
  (vec (map (fn [k] (keyword k)) (.split (name kw) " "))))

(defn to-form [rule key data]
  (cond
    (= rule :content ) (h/content (get-in data (keywords-vector key)))
    (= rule :html-content ) (h/html-content (get-in data (keywords-vector key)))
    (= rule :set-attr ) (apply h/set-attr (flatten (map (fn [[k v]] [k (get-in data (keywords-vector v))]) key)))
    :else (h/content (get-in data (keywords-vector key)))))

(defn enlive-template [{:keys [content meta]}]
  (enlive-str-template content [data]
    (map
      (fn [[selector rules]]
        [(keywords-vector selector)
         (apply h/do->
           (map
             (fn [[k v]] (to-form k v data)) rules))]) (:enlive meta))))