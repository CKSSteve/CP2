package com.uxb2b.ecp.interceptor;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.ILoginBusiness;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.model.User;
import com.uxb2b.ecp.service.AlgorithmService;
import com.uxb2b.ecp.service.SystemInfo;

public class SecurityManagerInterceptor implements UserDetailsService {

	private Logger log = LoggerFactory.getLogger(SecurityManagerInterceptor.class);

	@Autowired
	private ILoginBusiness iLoginBusiness;

	@Autowired
	private IUserLogBusiness iUserLogBusiness;

	@Autowired
	private AlgorithmService algorithmService;

	@Override
	public UserDetails loadUserByUsername(String userIdAndPassword) throws UsernameNotFoundException {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		UserProfileBean userProfile = null;
		String[] loginUser = userIdAndPassword.split("_");
		String userId = loginUser[0];
		String password = loginUser[1];
		String Msg = "";
		String pwdMD5 = algorithmService.toEncryptMD5(password);
		User user = iLoginBusiness.findByUserId(userId);
		if (user != null) {
			if (pwdMD5.equals(user.getPassword())) {
				long pwdChangeday = iLoginBusiness.pwdChangeday(user.getChangePasswordTime());
				if (user.getErrorCount() >= 3) {
					// 登入錯誤超過三次
					iUserLogBusiness.LoginErrorOverLog(user.getUserId(), user.getUserName());
					userProfile = new UserProfileBean(SystemInfo.LOGIN_MESSAGE_LOGINERROR_THREE);
					log.info(user.getUserId()+" 登入錯誤超過三次");
					throw new BadCredentialsException("登入錯誤超過三次");
				} else if (user.getStatus()==0) {
					iUserLogBusiness.LoginUserStop(user.getUserId(), user.getUserName());
					userProfile = new UserProfileBean(SystemInfo.LOGIN_MESSAGE_USER_STOP);
					log.info(user.getUserId()+" 帳號停用");
					throw new BadCredentialsException(user.getUserId()+" 帳號停用");
				} else {
					if (user.getLoginCount() == 0) {
						// 首次登入需修改密碼
						log.info(user.getUserId()+" 首次登入需修改密碼");
						Msg = SystemInfo.LOGIN_MESSAGE_FIRSYLOGIN;
					} else if (pwdChangeday < 0) {
						// 修改密碼時間超過90天
						log.info(user.getUserId()+" 修改密碼時間超過90天");
						Msg = SystemInfo.LOGIN_MESSAGE_OVERTHIRTYDAY;
					} else {
						// 登入成功
						log.info(user.getUserId()+" 登入成功");

						iLoginBusiness.loginSusses(user);
						iUserLogBusiness.LoginSuccessLog(user.getUserId(), user.getUserName());
					}
					authorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRoleId())));
					userProfile = new UserProfileBean(userId, password, user.getUserName(), user.getRoleId(),
							user.getRole().getRoleName(), pwdChangeday, authorities, Msg,user.getBranch().getChineseName(),user.getBranchId());
				}

			} else {
				iUserLogBusiness.LoginErrorLog(user.getUserId(), user.getUserName());
				iLoginBusiness.loginError(user);
				userProfile = new UserProfileBean(SystemInfo.LOGIN_MESSAGE_PASSWORDERROR);
				log.info(user.getUserId()+" 密碼錯誤");
				throw new BadCredentialsException("密碼錯誤");
			}
		} else {
			log.info("無此帳號 :"+userId);
			userProfile = new UserProfileBean(SystemInfo.LOGIN_MESSAGE_IDDERROR);
			throw new BadCredentialsException("無此帳號");
		}
		return userProfile;
	}

}
