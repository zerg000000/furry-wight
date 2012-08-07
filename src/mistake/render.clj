(ns mistake.render
  (:require [net.cgrand.enlive-html :as h]))

(defn apply-template [doc templates]
  "apply template to doc data structure"
  (let [ template-name (get-in doc [:meta :template])
         template-context (merge (:meta doc) doc)
         the-template (get templates template-name)]
    (the-template template-context)))

(defmacro enlive-str-template [source args forms]
  "transform (data)=>(nodes) to (data)=>(str)"
  `(comp (partial apply str) h/emit* (fn ~args (h/at* ~source ~forms))))

(defn enlive-template [doc]
  (enlive-str-template (:content doc) [data]
    (concat (map
              (fn [[selector [s t]]]
                (let [meta-key (keyword t)]
                  [[selector]
                   (cond
                     (= s "content")      (h/content (meta-key data))
                     (= s "html-content") (h/html-content (meta-key data))
                     :else (h/content (meta-key data)))])) (:meta doc)))))

(defn doc-template [^String name doc]
  (cond
    (.endsWith name ".html") (enlive-template doc)))