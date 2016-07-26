(defproject clj-airbrake "3.0.4.billfront"
  :description "Airbrake Client"
  :min-lein-version "2.0.0"
  :url "https://github.com/bmabey/clj-airbrake"
  :dependencies [[http-kit "2.1.19"]
                 [clj-stacktrace "0.2.8"]
                 [ring/ring-core "1.2.0"]
                 [org.clojure/clojure "1.7.0"]
                 [cheshire "5.5.0"]]
  :profiles {:dev
             {:resource-paths ["test-resources"]
              :dependencies [[enlive "1.1.4"]]}}
  :plugins [[s3-wagon-private "1.2.0"]]
  :repositories ~(let [split #(clojure.string/split %2 %1)
                       [username passphrase]
                       (->> (str (System/getenv "HOME") "/.aws/credentials") slurp
                            (split #"\n") rest
                            (map (comp last (partial split #" = "))))]
                   {"private" {:url "s3p://billfront-sftp/public/"
                               :sign-releases false
                               :username username
                               :passphrase passphrase}}))
