[@page title="Crawls"]

<h3>Crawls <a href="import" class="btn btn-default pull-right">Import from Heritrix</a></h3>

[#if crawls.isEmpty()]
    <p>No crawls found.</p>
[#else]
    [#include "/crawls/_list.ftl"]
    [@pagination crawlsPager.currentPage crawlsPager.lastPage /]
[/#if]


[/@page]