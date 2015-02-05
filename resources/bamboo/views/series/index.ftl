[@page title="Crawl Series"]
<table class="table">
    <thead>
        <tr><td>Name</td><td>Path</td></tr>
    </thead>
    <tbody>
        [#list seriesList as series]
            <tr>
                <td><a href="series/${series.id}">${series.name}</a></td>
                <td>${series.path}</td>
                <td><a href="series/${series.id}/edit">Edit</a></td>
            </tr>
        [/#list]
    </tbody>
</table>
[@pagination seriesPager.currentPage seriesPager.lastPage /]
<a href="series/new" class="btn btn-primary">New Crawl Series</a>
[/@page]