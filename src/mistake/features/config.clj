(ns mistake.features.config)

(defn publish-date-sort [articles]
  (reverse (sort-by (fn [article] (get-in article [:meta "publish-date"])) (vals articles))))

(def config
  {:config {:dir {:layouts-dir "layouts/"
                  :matters-dir "matters/"
                  :resources-dir "resources/"
                  :articles-dir "articles/"
                  :distribution-dir "distribution/"
                  :working-dir "work/"}
            :articles-sorter publish-date-sort}})

(defn wrap-prepare-config
  [handler & [opts]]
  (fn [req]
    (handler (merge config req))))