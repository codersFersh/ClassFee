import * as request from "@/network/request";
// import * as request



//用户登录
export function PostUserLogin(params) {
    return request.POST2(
        '/User/login',
        params
    )
}

//用户注销
export function PostUserlogout(params) {
    return request.POST2(
        '/User/logout',
        params
    )
}


//查询所有学生信息
export function getStuAll() {
    return request.GET(
        '/Student/stuAll',
    )
}

//根据学生姓名模糊查找
export function getStuName(params) {
    return request.GET(
        '/Student/stuName',
        params
    )
}

//查找所有班级
export function getGradeAll() {
    return request.GET(
        '/Grade/graAll',
    )
}


//查找所有角色
export function getRoleAll() {
    return request.GET(
        '/Role/roleAll',
    )
}

//添加学生
export function postStuAdd(params) {
    return request.POST2(
        '/Student/stuAdd',
        params
    )
}


//编辑学生
export function postStuEdit(params) {
    return request.POST2(
        '/Student/stuEdit',
        params
    )
}

//删除学生
export function postStuDel(params) {
    return request.POST2(
        '/Student/stuDel',
        params
    )
}


//根据学号查询学生
export function getStuId(params) {
    return request.GET(
        '/Student/stuId',
        params
    )
}


//统计学生总数
export function getstuCount() {
    return request.GET(
        '/Student/stuCount'
    )
}

// 查询所有班费记录
export function getCfAll() {
    return request.GET(
        '/ClassFee/cfAll'
    )
}

//新增班费记录
export function PostCfAdd(params) {
    return request.POST2(
        '/ClassFee/cfAdd',
        params
    )
}


//编辑班费
export function PostCfEdit(params) {
    return request.POST2(
        '/ClassFee/cfEdit',
        params
    )
}


// 删除班费
export function PostCfDel(params) {
    return request.POST2(
        '/ClassFee/cfDel',
        params
    )
}

// 模糊查找班费记录
export function getCfSearch(params) {
    return request.GET(
        '/ClassFee/cfSearch',
        params
    )
}

//Student表删除时查询该班费是否有记录
export function getDetCount(params) {
    return request.GET(
        '/Details/checkFid',
        params
    )
}
//ClassFee表删除时查询该班费学生是否有记录
export function getDetSid(params) {
    return request.GET(
        '/Details/checkSid',
        params
    )
}


// 查询该条缴费的学生缴费信息记录
export function getDetAll(params) {
    return request.GET(
        '/Details/detAll',
        params
    )
}


//新增学生缴费记录
export function PostDetAdd(params) {
    return request.POST2(
        '/Details/detAdd',
        params
    )
}


//编辑学生缴费
export function PostDetEdit(params) {
    return request.POST2(
        '/Details/detEdit',
        params
    )
}

// 删除学生缴费
export function PostDetDel(params) {
    return request.POST2(
        '/Details/detDel',
        params
    )
}


//查询所有班费支出
export function getExpAll() {
    return request.GET(
        '/Expend/expAll',
    )
}


//新增班费支出记录
export function PostExpAdd(params) {
    return request.POST2(
        '/Expend/expAdd',
        params
    )
}

//编辑班费支出
export function PostExpEdit(params) {
    return request.POST2(
        '/Expend/expEdit',
        params
    )
}

// 删除班费支出
export function PostExpDel(params) {
    return request.POST2(
        '/Expend/expDel',
        params
    )
}


// 模糊查找班费支出记录
export function getExpSearch(params) {
    return request.GET(
        '/Expend/expSearch',
        params
    )
}

//统计收入
export function getSumMoney() {
    return request.GET(
        '/Details/sumMoney'
    )
}

//统计支出
export function getSumGross() {
    return request.GET(
        '/Expend/sumGross'
    )
}

//预收班费
export function getSumFee() {
    return request.GET(
        '/ClassFee/sumFee'
    )
}
//查询一条班费记录中的缴费记录是否完成，做按钮禁用需要
export function getSumFIdMoney(params) {
    return request.GET(
        '/Details/sumFidMoney',
        params
    )
}