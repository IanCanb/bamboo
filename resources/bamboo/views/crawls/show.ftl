[@page title="Crawl: ${crawl.name}"]

<ol class="breadcrumb">
    <li><a href="crawls">Crawls</a></li>
    <li class="active">${crawl.name}</li>
</ol>

[#macro indexingProgress todo]
    [#if todo > 0]
        ${todo} warcs require indexing
        [@progress now=(crawl.warcFiles - todo) max=crawl.warcFiles /]

    [#else]
        Complete
    [/#if]
[/#macro]

<h3>${crawl.name} <a class="btn btn-default pull-right" href="crawls/${crawl.id}/edit">Edit</a></h3>

[#if crawl.state == 1]
    <div class="alert alert-info" role="alert">
        This crawl is currently being imported and the statistics below may be incomplete.
    </div>
[/#if]

[#if corruptWarcs > 0]
    <div class="alert alert-warning" role="alert">
        <strong>Possible WARC corruption:</strong> ${corruptWarcs} WARC files were unreadable when indexing this crawl.
    </div>
[/#if]

[#if warcsToBeCdxIndexed > 0 || warcsToBeSolrIndexed > 0]
    Indexing of this crawl has not finsihed.
    [#if warcsToBeCdxIndexed > 0]
    CDX: [@indexingProgress warcsToBeCdxIndexed /]
    [/#if]
    [#if warcsToBeSolrIndexed > 0]
    Solr: [@indexingProgress warcsToBeSolrIndexed /]
    [/#if]
[/#if]


<div class="row">
    <div class="col-md-8">
        <div class="description well">
            <div class="panel-body">
                [#noescape]${descriptionHtml!"No description."}[/#noescape]
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <table class="table">
            <colgroup>
                <col class="col-md-2">
            </colgroup>
            <tr><th>Collected</th>
                <td>2/8/2013&ndash;5/8/2013</td>
            </tr>
            <tr><th>Series</th><td><a href="series/${series.id}">${series.name}</a></td></tr>
            <tr>
                <th>Size</th>
                <td>
                    ${crawl.records} records; <abbr title="${crawl.recordBytes} bytes">${si(crawl.recordBytes)}B</abbr><br>
                    ${crawl.warcFiles} warc files; <abbr title="${crawl.warcSize} bytes">${si(crawl.warcSize)}B
                </td></tr>
            <tr>



            <!--

            -->
        </table>
    </div>
</div>








[/@page]