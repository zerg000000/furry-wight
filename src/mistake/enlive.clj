(ns mistake.enlive
  (:require [net.cgrand.enlive-html :as h]))

(defmacro enlive-str-template [source args forms]
  "transform (data)=>(nodes) to (data)=>(str)"
  `(comp (partial apply str) h/emit* (fn ~args (h/at* ~source ~forms))))

(defn to-selector [^String s]
  "Convert string to a vector of selector identity."
  (vec (map (fn [k] (keyword k)) (.split s " "))))

(defn to-form [rule key data]
  (cond
    (= rule "content") (h/content (get data key))
    (= rule "html-content") (h/html-content (get data key))
    (= rule "set-attr") (apply h/set-attr (flatten (map (fn [[k v]] [(keyword k) (get data v)]) key)))
    :else (h/content (get data key))))

(defn enlive-template [{:keys [content meta]}]
  (enlive-str-template content [data]
    (map
      (fn [[selector rules]]
        [(to-selector selector)
         (apply h/do->
           (map
             (fn [[k v]] (to-form k v data)) rules))]) (get meta "enlive"))))