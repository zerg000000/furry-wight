(ns mistake.core
  (:use [mistake io render]
        [mistake.features config article matter]))

(defn wrap-prepare-constants
  [handler & [opts]]
  (fn [req]
    (let [config (:config req)]
      (handler (assoc req :site {:now (java.util.Date.)})))))

(defn wrap-prepare-layouts
  [handler & [opts]]
  (fn [req]
    (let [dirs (get-in req [:config :dir ])]
      (handler
        (assoc req :layouts (into {} (pmap
                                       (fn [[k v]] [k (doc-template k v)])
                                       (doc-seq (:layouts-dir dirs)))))))))

(def gen
  (-> (fn [r] r)
    wrap-generate-articles
    wrap-generate-matters
    wrap-prepare-layouts
    wrap-prepare-matters
    wrap-prepare-articles
    wrap-prepare-config
    ))