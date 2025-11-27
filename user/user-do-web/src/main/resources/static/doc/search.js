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
    order: '6',
    link: '用户会话控制器',
    desc: '用户会话控制器',
    list: []
})
api[0].list[0].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/registrar/register',
    methodId: 'f5b9443341192f01f3d50c3cca53367a',
    desc: '注册',
});
api[0].list[0].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/registrar/root',
    methodId: 'f33861466d4b3803eaa672228710f721',
    desc: 'root',
});
api[0].list[0].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/registrar/logoff',
    methodId: '3108b855bb769fac933d42256d682ecb',
    desc: '下线',
});
api[0].list.push({
    alias: 'UserSessionController',
    order: '7',
    link: '用户会话控制器',
    desc: '用户会话控制器',
    list: []
})
api[0].list[1].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/session/login',
    methodId: '9945bf016f6bd8157887ca16d1c4a25c',
    desc: '登录',
});
api[0].list[1].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/session',
    methodId: 'e4345ae28aaeafc734ab2674061ba2aa',
    desc: 'current',
});
api[0].list.push({
    alias: 'IdCardUserController',
    order: '8',
    link: '证件用户api接口',
    desc: '证件用户API接口',
    list: []
})
api[0].list[2].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/id-card/user/{id}',
    methodId: 'e291eee97823f6d47c1fa9821e582205',
    desc: 'queryByUserid',
});
api[0].list[2].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/id-card/status',
    methodId: '2a276a0567e7440ebc015da34890ae14',
    desc: 'status',
});
api[0].list[2].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/id-card/authenticate',
    methodId: '1e642995d8fe00d5c38c51be5e1ec2e2',
    desc: 'authenticate',
});
api[0].list[2].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/id-card/userid-list',
    methodId: '70d4884a7854df44b1da2bf6f96c858a',
    desc: 'queryUseridByNameOfIdNoForAppid',
});
api[0].list.push({
    alias: 'UserController',
    order: '9',
    link: '用户会话控制器',
    desc: '用户会话控制器',
    list: []
})
api[0].list[3].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/{id}',
    methodId: '598dec50c4a4711e3bd1b3e4451e3f56',
    desc: '根据用户编号，查询主用户信息',
});
api[0].list[3].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/brief',
    methodId: '9cd69a5dfd06412711cf0ceb63dce048',
    desc: '用户简介信息',
});
api[0].list[3].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/phone',
    methodId: '5fbc9213a03af1e074ee8d0a0e83e685',
    desc: '获取指定会话用户手机号',
});
api[0].list[3].list.push({
    order: '4',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/nickname',
    methodId: 'e6256763af703475d093de86ad181854',
    desc: '更新当前用户昵称',
});
api[0].list[3].list.push({
    order: '5',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/avatar',
    methodId: '29534a49198467aeddf03fa7e2fe1f37',
    desc: '更新当前用户头像',
});
api[0].list[3].list.push({
    order: '6',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/user/avatar',
    methodId: 'b5d9e50d3d2bb5869e3aea1bfbf81f8e',
    desc: '上传当前用户头像并更新',
});
api[0].list.push({
    alias: 'ChlUserController',
    order: '10',
    link: '渠道用户api接口',
    desc: '渠道用户API接口',
    list: []
})
api[0].list[4].list.push({
    order: '1',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/chl-user/user/{userid}',
    methodId: '43f7704237e45ad319a88a098b3d290a',
    desc: 'queryByUserid',
});
api[0].list[4].list.push({
    order: '2',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/chl-user/user/{userid}/chl/{chl}/appid/{chlAppid}/app-type/{chlAppType}',
    methodId: '15776d4bac23fe7aff7b7a013fce2b23',
    desc: 'queryByUseridAndChlAndChlAppidAndChlAppType',
});
api[0].list[4].list.push({
    order: '3',
    deprecated: 'false',
    url: 'https://dev.gateway.api.asialjim.cn/api/domain/user/chl-user/userid',
    methodId: 'd4e9acb71766587b957c9fbe8e361bd0',
    desc: 'queryUseridByChlAppidTypeForAppid',
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