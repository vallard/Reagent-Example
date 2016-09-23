(ns testme.home
  (:require
    [reagent.core :as r]))

(defn display-apis
  "Displays APIs that match the search string"
  [search-string]
  (let [
      names ["Tom Davies" "Vallard Benincosa" "Ashley Roach" "Chris Rive..e.ereeree" "Yu Qi" "Schuey"]]
    [:div {:class "container-fluid"}
      (for [api-name names]
        (if (or (re-find (re-pattern (str "(?i)" search-string)) api-name)
                (= "" search-string))
          ^{ :key (.indexOf names api-name)}
            [:div {:class "col-sm-4"}
            [:div {:class "panel panel-default"}
              [:div {:class "panel-heading"} api-name]
              [:div {:class "panel-body"} 
                    (str api-name "'s API random description")]]]))]))
           
    

(defn show-home
  "Shows the landing page and a selection of the APIs available for use."
  []
  ;; First, display banner and search text box.
  (let [search-string (r/atom "")]
    (fn []
      [:div {:class "jumbotron " }
        [:div {:class "container-fluid"}
            [:div {:class "row"}]
              [:div {:class "col-sm-2"}]
              [:div {:class "col-sm-8"}
                [:h2 [:center "Cisco Pipeline" ]]
                [:p {:class "center"} "Search The API Network" ]
              [:div {:class "row"}]
              [:div {:class "col-sm-2"}]
              [:div {:class "col-sm-8"}
                [:input.form-control {
                  :type "text"
                  :placeholder "Go ahead! Surf the APIs..."
                  :value @search-string
                  :on-change #(reset! search-string (-> % .-target .-value))
                }]
           ]
          ]
      ]
    [:div {:class "page-header"}]
    [display-apis @search-string]])))
