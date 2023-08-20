//package com.atbanfei.ssm.interceptor;
//
//
//import com.atbanfei.ssm.anno.RequirePermission;
//import com.itheima.pojo.Employee;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class PermissionInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //handler就是mvc方法，他的类型是？
//        if(handler instanceof HandlerMethod){
//            HandlerMethod method=(HandlerMethod) handler;
//            //这个方法是否有RequirePermission注解
//            //尝试获取RequirePermission注解
//            RequirePermission permission=method.getMethodAnnotation(RequirePermission.class);
//            if(permission==null){
//                //没有RequirePermission注解
//
//            }
//            else {
//                //有注解
//                String v=permission.value();
//                //打印当前路径和需要的权限
//                String uri=request.getRequestURI();
//                System.out.println(uri+"------->所需权限："+v);
//                //验证用户是否有权限
//
//
//                HttpSession session=request.getSession();
//                Employee curUser=(Employee) session.getAttribute("CURRENT_USER");
//                if(curUser==null){
//                    //没有登录
//
//                    String root=request.getContextPath();//项目根路径
//                    response.sendRedirect(root+"/auth/login");//重定向到登录界面
//                    return false;
//                }
//                else {
//                    if (v.equals("")){
//                        return true;
//                    }else {
//                        boolean hasPermission= curUser.hasPermission(v);
//                        if (hasPermission){
//                            return true;
//                        }else {
//                            request.getRequestDispatcher("/WEB-INF/pages/common/NoPermission.jsp").forward(request,response);
//                        }
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }
//}
