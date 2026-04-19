<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center mt-5">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm border-danger">
                <div class="card-body text-center">
                    <h1 class="display-4 text-danger mb-3">${status!500}</h1>
                    <h3 class="mb-3">${error!"Error"}</h3>
                    <p class="text-muted mb-3">${message!"Something went wrong"}</p>

                    <#if path??>
                        <p class="small text-secondary mb-4">
                            <strong>Path:</strong> ${path}
                        </p>
                    </#if>

                    <div class="d-flex justify-content-center gap-2">
                        <a href="/" class="btn btn-primary">На главную</a>
                        <a href="javascript:history.back()" class="btn btn-outline-secondary">Назад</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>