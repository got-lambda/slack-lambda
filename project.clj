(defproject slack-lambda "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://slack-lambda.herokuapp.com"
  :license {:name "FIXME: choose"
            :url "http://example.com/FIXME"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [ring/ring-devel "1.2.2"]
                 [ring-basic-authentication "1.0.5"]
                 [environ "0.5.0"]
                 [http-kit "2.1.16"]
                 [cheshire "5.3.1"]
                 [instaparse "1.3.4"]
                 [com.cemerick/drawbridge "0.0.6"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.2.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "slack-lambda-standalone.jar"
  :profiles {:production {:env {:production true}}})
