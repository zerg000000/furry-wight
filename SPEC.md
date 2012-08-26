Specification
=============

Matter
------

It is a mixture of texts, graphics, medias.
**Matter** is something we want to present and stored as readable text for futher processing.
**Matter** always provide features other than providing content of a book.
**Matter** always serve for non sequentially usage.
For example, Front Cover, Back Cover, Author's Words.

Article
----

Sometimes called content. It is a mixture of texts, graphics, medias.
**Article** is the thing we want to present and stored as readable text for further processing.
Reader read **Article** in some sort of order sequentially in most of the use cases.

Auto Generated Matter/Article
--------------------------

Index Page, Glossary, Bibliography, Reference would be auto generated
using specified **Layout**.

Workflow
--------

1. Generate Presentation of **Matter** and **Article**
2. Generate Statistics Information for **Matter** and **Article**
3. Generate Presentation of **Snippet**
4. Generate Presentation of **Layout**
5. Generate media from **Layout** by filling in **Matter**/**Article**/**Snippet**


Folder Structure
----------------

**Article**, **Matter**, **Layout** would have a same name json in the same directory.
The content of json will be used as meta data. All static medias like images, js, css
will be located in `resources/` folder. A `project.clj` will be located at the root of
the folder structure and have all the config options of generator.

    articles/
      2012-08-21-hot-news-is-old-news.md
      2012-08-21-hot-news-is-old-news.md.json
    matters/
      about-us.md
      about-us.md.json
    resources/
      img/
        background-image.png
      css/
        style.css
        main.less
      js/
        jquery.min.js
    layouts/
      article.mustache
      article.mustache.json
      index.html
      index.html.json
    work/
    distribution/
    project.clj

Data Structure
--------------

* ###core###

        {
          :config {
                    :dir { ... }
                    :article-sorter ...
                  }
          :matters { ... }
          :articles { ... }
          :snippets { ... }
          :layouts { ... }
        }

* ###document (e.g article, matter, layout)###

        {
          :content { ... }
          :meta { ... }
        }

* ###article###

        {
          :content { ... }
          :meta {
                  :id ...
                  :title ...
                  :author(optional) ...
                  :publish-date ...
                  :layout ...
                  :tags(optional) [ ... ]
                  :categories(optional) [ ... ]
                }
        }

* ###article rendering context###

        {
          ... meta attributes ...
          ... snippets ...
          :content { ... }
          :id ...
          :link ...
          :art-next-link ...
          :art-prev-link ...
        }

