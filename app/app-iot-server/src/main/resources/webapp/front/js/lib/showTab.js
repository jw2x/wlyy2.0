/**
 * Created by JKZL-A on 2017/10/24.
 */
define(['jquery'], function ($) {
    var getTabTmp = function (url, name) {
            return '<a href="#" class="active J_menuTab" data-id="'+ url +'">'+ name +' <i class="fa fa-close"></i></a>';
        },
        getIframTmp = function (len, url) {
            return '<iframe class="J_iframe" name="iframe'+ len +'" src="'+ url +'" width="100%" height="100%" frameborder="0" data-id="'+ url +'" seamless>';
        };

    var showTab = function (opt) {
        var me = this,
            $pageTabsContent = $('.page-tabs-content'),
            $contentMain = $('#content-main'),
            cLen = 0,
            hasTab = false;
        $.each($pageTabsContent.find("a"), function (k, obj) {
            if ($(obj).attr('data-id') == opt.url) {
                hasTab = true;
                $(obj).trigger('click');
                return;
            }
        });
        if (hasTab) return;
        $pageTabsContent.children().removeClass('active');
        $pageTabsContent.append(getTabTmp(opt.url, opt.name));
        cLen = $contentMain.children().length;
        $contentMain.children().hide();
        $contentMain.append(getIframTmp(cLen, opt.url));
    }
    return showTab;
});