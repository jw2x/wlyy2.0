/**
 * Created by JKZL-A on 2017/10/23.
 */
require.config({
    urlArgs: 'bust=' +  (new Date()).getTime(),//测试打开
    baseUrl: '/iot/front/js/',
    paths: {
        'jquery': 'lib/jquery.min',
        'bootstrap': 'lib/bootstrap.min',
        'bootstrap-table': 'lib/plugins/bootstrap-table/bootstrap-table.min',
        'dataTables': 'lib/plugins/dataTables/jquery.dataTables',
        'validate': 'lib/plugins/validate/jquery.validate.min',
        'metisMenu': 'lib/plugins/metisMenu/jquery.metisMenu',
        'slimscroll': 'lib/plugins/slimscroll/jquery.slimscroll.min',
        'layer': 'lib/plugins/layer/layer.min',
        'echarts': 'lib/plugins/echarts/echarts3/echarts.min',
        'macarons': 'lib/plugins/echarts/echarts3/macarons',
        'contabs': 'lib/contabs.min',
        'vue': 'lib/plugins/vue/vue.min',
        'showTab': 'lib/showTab',
        'promise': 'lib/es6-promise',
        'underscore': 'lib/underscore',
        'jsHelper': 'common/jsHelper',
        'apiServer': 'common/apiServer',
        'hplus': 'lib/hplus.min'
    },
    shim: {
        'bootstrap': {
            deps: ['jquery']
        },
        'bootstrap-table': {
            deps: ['jquery']
        },
        'dataTables': {
            deps: ['jquery']
        },
        'metisMenu': {
            deps: ['jquery']
        },
        'layer': {
            deps: ['jquery'],
            exports: 'Layer'
        },
        'slimscroll': {
            deps: ['jquery']
        },
        'contabs': {
            deps: ['jquery']
        },
        'showTab': {
            deps: ['jquery'],
            exports: 'ShowTab'
        },
        'jsHelper': {
            deps: ['jquery', 'layer'],
            exports: 'JsHelper'
        },
        'apiServer': {
            deps: ['jquery', 'promise', 'layer', 'jsHelper'],
            exports: 'apiServer'
        },
        'hplus': {
            deps: ['layer', 'jquery', 'bootstrap', 'metisMenu', 'slimscroll', 'contabs']
        },
        'macarons': {
            deps: ['echarts']
        }
    },
    waitSeconds: 30
});