(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[55],{tLGd:function(e,a,t){"use strict";var l=t("284h"),s=t("TqRt");Object.defineProperty(a,"__esModule",{value:!0}),a.default=void 0,t("IzEo");var n=s(t("bx4M"));t("7Kak");var d=s(t("9yH6")),u=l(t("q1tI")),r=t("LLXN"),o=s(t("lVjH")),i=t("KTCi"),f=s(t("Umy/")),m=(0,u.memo)(function(e){var a=e.dropdownGroup,t=e.salesType,l=e.loading,s=e.salesPieData,m=e.handleChangeSalesType;return u.default.createElement(n.default,{loading:l,className:o.default.salesCard,bordered:!1,title:u.default.createElement(r.FormattedMessage,{id:"app.analysis.the-proportion-of-sales",defaultMessage:"The Proportion of Sales"}),bodyStyle:{padding:24},extra:u.default.createElement("div",{className:o.default.salesCardExtra},a,u.default.createElement("div",{className:o.default.salesTypeRadio},u.default.createElement(d.default.Group,{value:t,onChange:m},u.default.createElement(d.default.Button,{value:"all"},u.default.createElement(r.FormattedMessage,{id:"app.analysis.channel.all",defaultMessage:"ALL"})),u.default.createElement(d.default.Button,{value:"online"},u.default.createElement(r.FormattedMessage,{id:"app.analysis.channel.online",defaultMessage:"Online"})),u.default.createElement(d.default.Button,{value:"stores"},u.default.createElement(r.FormattedMessage,{id:"app.analysis.channel.stores",defaultMessage:"Stores"}))))),style:{marginTop:24}},u.default.createElement("div",{style:{minHeight:380}},u.default.createElement("h4",{style:{marginTop:8,marginBottom:32}},u.default.createElement(r.FormattedMessage,{id:"app.analysis.sales",defaultMessage:"Sales"})),u.default.createElement(i.Pie,{hasLegend:!0,subTitle:u.default.createElement(r.FormattedMessage,{id:"app.analysis.sales",defaultMessage:"Sales"}),total:function(){return u.default.createElement(f.default,null,s.reduce(function(e,a){return a.y+e},0))},data:s,valueFormat:function(e){return u.default.createElement(f.default,null,e)},height:248,lineWidth:4})))}),c=m;a.default=c}}]);