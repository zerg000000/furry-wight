Specification
=============


Matter
------
It is a mixture of texts, graphics, medias.
**Matter** is something we want to present and stored as readable text for futher processing.
**Matter** always provide features other than providing content of a book.
**Matter** always serve for non sequentially usage.
For example, Front Cover, Back Cover, Author's Words.

Page
----
Sometimes called content. It is a mixture of texts, graphics, medias.
**Page** is the thing we want to present and stored as readable text for further processing.
Reader read **Page** in some sort of order sequentially in most of the use cases.

Auto Generated Matter/Page
--------------------------
Index Page, Glossary, Bibliography, Reference would be auto generated
using specified **Template**.

Workflow
--------

1. Generate Presentation of **Matter** and **Page**
2. Generate Statistics Information for **Matter** and **Page**
3. Generate Presentation of **Snippet**
4. Generate Presentation of **Template**/**Layout**
5. Generate media from **Template**/**Layout** by filling in **Matter**/**Page**/**Snippet**


Page Rendering Default Parameters
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








