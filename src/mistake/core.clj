(ns mistake.core
  (:use [mistake io render]
        [mistake.features config]))

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

(defn wrap-prepare-articles
  [handler & [opts]]
  (fn [req]
    (let [config (:config req)
          dirs (get-in req [:config :dir ])
          sorter (get config :articles-sorter )
          sorted-articles (sorter (doc-seq (:articles-dir dirs)))]
      (handler
        (assoc req :articles (into {}
                               (map-indexed
                                 (fn [idx itm] [idx itm]) sorted-articles)))))))

(defn wrap-prepare-matters
  [handler & [opts]]
  (fn [req]
    (let [dirs (get-in req [:config :dir ])]
      (handler (assoc req :matters (doc-seq (:matters-dir dirs)))
        ))))

(defn title-id [^String title] (.replace (.toLowerCase title) " " "-"))

(defn generate-articles [articles layouts snippets config]
  (pmap
    (fn [[idx article]]
      (let [outname (title-id (get-in article [:meta "title"]))
            outdir (get-in config [:dir :working-dir ])
            layout-name (get-in article [:meta "layout"])
            render-context (merge (:meta article)
          snippets
          {"content" (:content article)
           "art-next-link" "not yet implement"
           "art-prev-link" "not yet implement"})]
        (spit (str outdir outname ".html")
          ((get layouts layout-name) render-context))
        )) articles))

(defn wrap-generate-articles
  [handler & [opts]]
  (fn [req]
    (let [config (:config req)
          articles (:articles req)
          layouts (:layouts req)
          snippets (:snippets req)]
      (handler req)
      (generate-articles articles layouts snippets config))))

(defn wrap-generate-matters
  [handler & [opts]]
  (fn [req]
    (let [config (:config req)
          matters (:matters req)
          layouts (:layouts req)
          snippets (:snippets req)]
      (handler req)
      (generate-articles matters layouts snippets config))))

(def gen
  (-> (fn [r] r)
    wrap-generate-articles
    wrap-generate-matters
    wrap-prepare-layouts
    wrap-prepare-matters
    wrap-prepare-articles
    wrap-prepare-config
    ))