(ns mistake.features.article
  (use [mistake.io :only (doc-seq)]))

(defn title-id [^String title] (if title (.replace (.toLowerCase title) " " "-")))

(defn generate-articles [articles layouts snippets config]
  (pmap
    (fn [[idx article]]
      (let [outname (title-id (get-in article [:meta :title ]))
            outdir (get-in config [:dir :working-dir ])
            layout-name (get-in article [:meta :layout ])
            render-context (merge (:meta article)
          snippets
          {:content (:content article)
           :art-next-link "not yet implement"
           :art-prev-link "not yet implement"})]
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