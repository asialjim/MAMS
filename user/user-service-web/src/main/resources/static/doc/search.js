/*
 *    Copyright 2014-2025 <a href="mailto:asialjim@qq.com">Asial Jim</a>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

let api = [];
const apiDocListSize = 1
api.push({
    name: 'default',
    order: '1',
    list: []
})
api[0].list.push({
    alias: 'UserRegistrarController',
    order: '13',
    link: '用户会话控制器',
    desc: '用户会话控制器',
    list: []
})
api[0].list[0].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/rest/user/registrar/register',
    methodId: 'f5b9443341192f01f3d50c3cca53367a',
    desc: '注册',
});
api[0].list[0].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/rest/user/registrar/logoff',
    methodId: '8a4d38750f7259bf446bb79011e579e9',
    desc: '下线',
});
api[0].list.push({
    alias: 'UserSessionController',
    order: '14',
    link: '用户会话控制器',
    desc: '用户会话控制器',
    list: []
})
api[0].list[1].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/rest/user/session/login',
    methodId: '9945bf016f6bd8157887ca16d1c4a25c',
    desc: '登录',
});
api[0].list[1].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/rest/user/session',
    methodId: 'e4345ae28aaeafc734ab2674061ba2aa',
    desc: '获取当前用户会话信息',
});
api[0].list.push({
    alias: 'IdCardUserController',
    order: '15',
    link: '证件用户api接口',
    desc: '证件用户API接口',
    list: []
})
api[0].list[2].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/rest/user/id-card/status',
    methodId: '2e6d3b509aae67d9b71d231f19093e9a',
    desc: '获取用户实名状态',
});
api[0].list[2].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/rest/user/id-card/authenticate',
    methodId: 'ae1dbbfb88338a29a6e07f3152961956',
    desc: '用户实名认证',
});
api[0].list.push({
    alias: 'UserController',
    order: '16',
    link: '用户会话控制器',
    desc: '用户会话控制器',
    list: []
})
api[0].list[3].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/rest/user/brief',
    methodId: '33ed1b2e13375fb3ccb7bbad5eda4fb1',
    desc: '获取当前用户信息摘要，包括：手机号、昵称和头像',
});
api[0].list[3].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/rest/user/nickname',
    methodId: '6e184fee654845ebdf56fc8d61a198aa',
    desc: '修改用户昵称',
});
api[0].list[3].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/rest/user/avatar',
    methodId: '61f19fe5e64ab9188ef54a8cb0e10d16',
    desc: '修改用户头像信息',
});
api[0].list[3].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/rest/user/avatar',
    methodId: 'fed8263d087f545a9d5e6dfa5b1ff39c',
    desc: '上传用户头像信息',
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