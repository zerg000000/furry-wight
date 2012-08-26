Specification
=============

Data Structure
--------------
* core

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

* document (e.g article, matter, layout)

        {
          :content { ... }
          :meta { ... }
        }

* article

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


Article Rendering Default Parameters
---------------------------------

|name          |  description
 ------------- | -------------------------------------------------
|content       | content that direct place in template
|url           | url for accessing this page
|title         | title from file name or meta data json
|date          | publish date from file name or meta data json format YYYY-MM-DD HH:MM:SS
|id            |
|categories    | the list of categories to which this page belongs to
|tags          | the list of tags to which this page belongs to
 -------------- --------------------------------------------------

 Paginator
 ---------

 |name          | description
  -------------- ------------
 |per-page      |Number of posts per
 |posts         |
 |total_posts   |
 |total_pages   |
 |page          |
 |previous_page |
 |next_page     |
  -------------- ------------








