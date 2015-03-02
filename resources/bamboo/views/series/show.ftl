[@page title="Series: ${series.name}"]

<table class="table">
    <colgroup>
        <col class="col-md-2">
    </colgroup>
    <tr><th>WARC Records</th><td>${series.records} records, <abbr title="${series.recordBytes} bytes">${si(series.recordBytes)}B</abbr> (uncompressed)</td></tr>
    <tr><th>WARC Files</th><td>${series.warcFiles} warcs, <abbr title="${series.warcSize} bytes">${si(series.warcSize)}B</abbr> (compressed)</td></tr>
    <tr><th>Collections</th>
        <td>
            <ul>
                [#list collections as collection]
                <li><a href="collections/${collection.id}">${collection.name}</a>
                    [#if collection.urlFilters?has_content] (filtered)
                    <div class="text-indent-8">
                        <pre>${collection.urlFilters}</pre>
                    </div>
                    [/#if]
                [/#list]
            </ul>
        </td>
    </tr>
</table>

<a class="btn btn-primary" href="series/${series.id}/edit">Edit Series</a>

<h2>Crawls</h2>

[#include "/crawls/_list.ftl"]

<p></p><a href="import?crawlSeries=${series.id}" class="btn btn-primary">Import Crawl from Heritrix</a></p>

[/@page]