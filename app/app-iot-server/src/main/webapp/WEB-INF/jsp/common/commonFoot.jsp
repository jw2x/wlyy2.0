<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/commonInclude.jsp" %>

<script src="${staticRoot}/js/lib/jquery.min.js"></script>
<script>
    $.extend({
        Context: {
            PATH: '${contextRoot}',
            STATIC_PATH: '${staticRoot}'
        }
    })
</script>


<script>
    (function ($, win) {
        $(function () {
            var $title = $('title'),
                    $headTitleTarget = $('[data-head-title="true"]');
            document.title = $headTitleTarget.text();
        });
    })(jQuery, window);
</script>