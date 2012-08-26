(ns mistake.features.matter
  (use [mistake.features.article :only (generate-articles)]
    [mistake.io :only (doc-seq)]))

(defn wrap-prepare-matters
  [handler & [opts]]
  (fn [req]
    (let [dirs (get-in req [:config :dir ])]
      (handler (assoc req :matters (doc-seq (:matters-dir dirs)))
        ))))

(defn wrap-generate-matters
  [handler & [opts]]
  (fn [req]
    (let [config (:config req)
          matters (:matters req)
          layouts (:layouts req)
          snippets (:snippets req)]
      (handler req)
      (generate-articles matters layouts snippets config))))