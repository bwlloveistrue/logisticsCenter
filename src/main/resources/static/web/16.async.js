(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[16],{ZOrW:function(e,a,t){"use strict";var s=t("TqRt"),r=t("284h");Object.defineProperty(a,"__esModule",{value:!0}),a.default=void 0,t("14J3");var n=s(t("BMrR"));t("jCWc");var l=s(t("kPKH"));t("qVdP");var i=s(t("jsC+"));t("Pwec");var c=s(t("CtXQ"));t("lUTK");var d,u,o,f=s(t("BvKs")),p=s(t("lwsE")),h=s(t("W8MJ")),m=s(t("a1gu")),g=s(t("Nsbk")),y=s(t("7W2i")),b=r(t("q1tI")),v=t("MuoO"),k=s(t("v99g")),D=t("+n12"),T=s(t("lVjH")),E=s(t("xqX8")),j=b.default.lazy(function(){return Promise.all([t.e(0),t.e(50)]).then(t.t.bind(null,"Y65U",7))}),C=b.default.lazy(function(){return Promise.all([t.e(0),t.e(51)]).then(t.t.bind(null,"20K/",7))}),P=b.default.lazy(function(){return Promise.all([t.e(0),t.e(52)]).then(t.t.bind(null,"b2ve",7))}),S=b.default.lazy(function(){return Promise.all([t.e(0),t.e(53)]).then(t.t.bind(null,"tLGd",7))}),x=b.default.lazy(function(){return Promise.all([t.e(0),t.e(54)]).then(t.t.bind(null,"Jqna",7))}),R=(d=(0,v.connect)(function(e){var a=e.chart,t=e.loading;return{chart:a,loading:t.effects["chart/fetch"]}}),d((o=function(e){function a(){var e,t;(0,p.default)(this,a);for(var s=arguments.length,r=new Array(s),n=0;n<s;n++)r[n]=arguments[n];return t=(0,m.default)(this,(e=(0,g.default)(a)).call.apply(e,[this].concat(r))),t.state={salesType:"all",currentTabKey:"",rangePickerValue:(0,D.getTimeDistance)("year")},t.handleChangeSalesType=function(e){t.setState({salesType:e.target.value})},t.handleTabChange=function(e){t.setState({currentTabKey:e})},t.handleRangePickerChange=function(e){var a=t.props.dispatch;t.setState({rangePickerValue:e}),a({type:"chart/fetchSalesData"})},t.selectDate=function(e){var a=t.props.dispatch;t.setState({rangePickerValue:(0,D.getTimeDistance)(e)}),a({type:"chart/fetchSalesData"})},t.isActive=function(e){var a=t.state.rangePickerValue,s=(0,D.getTimeDistance)(e);return a[0]&&a[1]&&a[0].isSame(s[0],"day")&&a[1].isSame(s[1],"day")?T.default.currentDate:""},t}return(0,y.default)(a,e),(0,h.default)(a,[{key:"componentDidMount",value:function(){var e=this.props.dispatch;this.reqRef=requestAnimationFrame(function(){e({type:"chart/fetch"})})}},{key:"componentWillUnmount",value:function(){var e=this.props.dispatch;e({type:"chart/clear"}),cancelAnimationFrame(this.reqRef),clearTimeout(this.timeoutId)}},{key:"render",value:function(){var e,a=this.state,t=a.rangePickerValue,s=a.salesType,r=a.currentTabKey,d=this.props,u=d.chart,o=d.loading,p=u.visitData,h=u.visitData2,m=u.salesData,g=u.searchData,y=u.offlineData,v=u.offlineChartData,D=u.salesTypeData,R=u.salesTypeDataOnline,w=u.salesTypeDataOffline;e="all"===s?D:"online"===s?R:w;var V=b.default.createElement(f.default,null,b.default.createElement(f.default.Item,null,"\u64cd\u4f5c\u4e00"),b.default.createElement(f.default.Item,null,"\u64cd\u4f5c\u4e8c")),q=b.default.createElement("span",{className:T.default.iconGroup},b.default.createElement(i.default,{overlay:V,placement:"bottomRight"},b.default.createElement(c.default,{type:"ellipsis"}))),I=r||y[0]&&y[0].name;return b.default.createElement(k.default,null,b.default.createElement(b.Suspense,{fallback:b.default.createElement(E.default,null)},b.default.createElement(j,{loading:o,visitData:p})),b.default.createElement(b.Suspense,{fallback:null},b.default.createElement(C,{rangePickerValue:t,salesData:m,isActive:this.isActive,handleRangePickerChange:this.handleRangePickerChange,loading:o,selectDate:this.selectDate})),b.default.createElement(n.default,{gutter:24},b.default.createElement(l.default,{xl:12,lg:24,md:24,sm:24,xs:24},b.default.createElement(b.Suspense,{fallback:null},b.default.createElement(P,{loading:o,visitData2:h,selectDate:this.selectDate,searchData:g,dropdownGroup:q}))),b.default.createElement(l.default,{xl:12,lg:24,md:24,sm:24,xs:24},b.default.createElement(b.Suspense,{fallback:null},b.default.createElement(S,{dropdownGroup:q,salesType:s,loading:o,salesPieData:e,handleChangeSalesType:this.handleChangeSalesType})))),b.default.createElement(b.Suspense,{fallback:null},b.default.createElement(x,{activeKey:I,loading:o,offlineData:y,offlineChartData:v,handleTabChange:this.handleTabChange})))}}]),a}(b.Component),u=o))||u),w=R;a.default=w},lVjH:function(e,a,t){e.exports={iconGroup:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-iconGroup",rankingList:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-rankingList",rankingItemNumber:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-rankingItemNumber",active:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-active",rankingItemTitle:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-rankingItemTitle",salesExtra:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-salesExtra",currentDate:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-currentDate",salesCard:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-salesCard",salesBar:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-salesBar",salesRank:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-salesRank",salesCardExtra:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-salesCardExtra",salesTypeRadio:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-salesTypeRadio",offlineCard:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-offlineCard",trendText:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-trendText",rankingTitle:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-rankingTitle",salesExtraWrap:"antd-pro-main-resources-src4js-src-pages-dashboard-analysis-salesExtraWrap"}}}]);