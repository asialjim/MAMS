let api = [];
const apiDocListSize = 1
api.push({
    name: 'default',
    order: '1',
    list: []
})
api[0].list.push({
    alias: 'OrgController',
    order: '1',
    link: '机构_服务',
    desc: '机构 服务',
    list: []
})
api[0].list[0].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/org/{id}',
    methodId: 'fe6c5a07628da534c89f68077b3f63a6',
    desc: '按id查询',
});
api[0].list.push({
    alias: 'AppController',
    order: '2',
    link: 'app_服务',
    desc: 'APP 服务',
    list: []
})
api[0].list[1].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/{id}',
    methodId: 'ae76c4439b4edf90a4e0ee4700bd39bf',
    desc: '按id查询',
});
api[0].list[1].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app',
    methodId: 'f7ef1b25768e7b4b9e7abfa752e3ce58',
    desc: 'save',
});
api[0].list[1].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app',
    methodId: 'fcb21fd0a38c9913dbdca1581cad113d',
    desc: 'update',
});
api[0].list[1].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/name/root',
    methodId: '80d2f71f9db8f1a2f81d7536cac558e9',
    desc: 'getRootApp',
});
api[0].list[1].list.push({
    order: '5',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/by-org-id/{id}',
    methodId: '8f7d8680060d2e989d69043d7fb3eafd',
    desc: '按组织id查询',
});
api[0].list[1].list.push({
    order: '6',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/list',
    methodId: '55cf6d672d461b16ca3a29c32cceabc8',
    desc: 'list',
});
api[0].list.push({
    alias: 'ChlAppController',
    order: '3',
    link: '渠道_app_服务',
    desc: '渠道 APP 服务',
    list: []
})
api[0].list[2].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/chl/{id}',
    methodId: '23eddc58e9d3778965e85099d30a3740',
    desc: '按id查询',
});
api[0].list[2].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/chl/app/{id}',
    methodId: '3d1895677fc506741c60e1a2d6ecfba2',
    desc: '按appid查询',
});
api[0].list[2].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/chl/app/{id}/chl/{chl}',
    methodId: 'cab0d0aa775ad41999f4717231e476b5',
    desc: '按appid和CHL查询',
});
api[0].list[2].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/chl/app/{id}/chl/{chl}/chl-appid/{chlAppid}',
    methodId: '248669bc28e8a416fba0f654ff833e8d',
    desc: '按appid、CHL和CHL appid查询',
});
api[0].list[2].list.push({
    order: '5',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/chl/app/{id}/chl/{chl}/chl-app-type/{chlAppType}',
    methodId: 'af568c92b756dbf469bbb5a853168b8b',
    desc: 'queryByAppidAndChlAndChlAppType',
});
api[0].list[2].list.push({
    order: '6',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/chl/app/{id}/root',
    methodId: '99fcbd9bb9546d456d65f04bcae98305',
    desc: 'queryRootByAppid',
});
api[0].list[2].list.push({
    order: '7',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/chl/org/{id}',
    methodId: 'f9cd3a322f7b2bf0edd86233254a352d',
    desc: '按组织id查询',
});
api[0].list[2].list.push({
    order: '8',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/chl/chl/{chl}/appid/{appid}',
    methodId: 'b5fd129101a6b43a5f3ab2b2b0f7458d',
    desc: '按CHL和CHL appid查询',
});
api[0].list[2].list.push({
    order: '9',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/chl/chl/{chl}/index/{index}',
    methodId: '1fe0dd4c83086dab97f7583d2f0f808e',
    desc: '按CHL和CHL索引查询',
});
api[0].list[2].list.push({
    order: '10',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/app/chl/chl/{chl}',
    methodId: 'c88634190db504b6d23eb3b08ad08ae9',
    desc: '按CHL查询',
});
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code === 13) {
        const search = document.getElementById('search');
        const searchValue = search.value.toLocaleLowerCase();

        let searchGroup = [];
        for (let i = 0; i < api.length; i++) {

            let apiGroup = api[i];

            let searchArr = [];
            for (let i = 0; i < apiGroup.list.length; i++) {
                let apiData = apiGroup.list[i];
                const desc = apiData.desc;
                if (desc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                    searchArr.push({
                        order: apiData.order,
                        desc: apiData.desc,
                        link: apiData.link,
                        alias: apiData.alias,
                        list: apiData.list
                    });
                } else {
                    let methodList = apiData.list || [];
                    let methodListTemp = [];
                    for (let j = 0; j < methodList.length; j++) {
                        const methodData = methodList[j];
                        const methodDesc = methodData.desc;
                        if (methodDesc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                            methodListTemp.push(methodData);
                            break;
                        }
                    }
                    if (methodListTemp.length > 0) {
                        const data = {
                            order: apiData.order,
                            desc: apiData.desc,
                            link: apiData.link,
                            alias: apiData.alias,
                            list: methodListTemp
                        };
                        searchArr.push(data);
                    }
                }
            }
            if (apiGroup.name.toLocaleLowerCase().indexOf(searchValue) > -1) {
                searchGroup.push({
                    name: apiGroup.name,
                    order: apiGroup.order,
                    list: searchArr
                });
                continue;
            }
            if (searchArr.length === 0) {
                continue;
            }
            searchGroup.push({
                name: apiGroup.name,
                order: apiGroup.order,
                list: searchArr
            });
        }
        let html;
        if (searchValue === '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchGroup,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            let $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiGroups, liClass, display) {
    let html = "";
    if (apiGroups.length > 0) {
        if (apiDocListSize === 1) {
            let apiData = apiGroups[0].list;
            let order = apiGroups[0].order;
            for (let j = 0; j < apiData.length; j++) {
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#' + apiData[j].alias + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                html += '<ul class="sectlevel2" style="'+display+'">';
                let doc = apiData[j].list;
                for (let m = 0; m < doc.length; m++) {
                    let spanString;
                    if (doc[m].deprecated === 'true') {
                        spanString='<span class="line-through">';
                    } else {
                        spanString='<span>';
                    }
                    html += '<li><a href="#' + doc[m].methodId + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                }
                html += '</ul>';
                html += '</li>';
            }
        } else {
            for (let i = 0; i < apiGroups.length; i++) {
                let apiGroup = apiGroups[i];
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#_'+apiGroup.order+'_' + apiGroup.name + '">' + apiGroup.order + '.&nbsp;' + apiGroup.name + '</a>';
                html += '<ul class="sectlevel1">';

                let apiData = apiGroup.list;
                for (let j = 0; j < apiData.length; j++) {
                    html += '<li class="'+liClass+'">';
                    html += '<a class="dd" href="#' + apiData[j].alias + '">' +apiGroup.order+'.'+ apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                    html += '<ul class="sectlevel2" style="'+display+'">';
                    let doc = apiData[j].list;
                    for (let m = 0; m < doc.length; m++) {
                       let spanString;
                       if (doc[m].deprecated === 'true') {
                           spanString='<span class="line-through">';
                       } else {
                           spanString='<span>';
                       }
                       html += '<li><a href="#' + doc[m].methodId + '">'+apiGroup.order+'.' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                   }
                    html += '</ul>';
                    html += '</li>';
                }

                html += '</ul>';
                html += '</li>';
            }
        }
    }
    return html;
}