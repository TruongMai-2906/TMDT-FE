import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { NotifySuccess, NotifyError } from '~/Utils/Notice';

import { Form, Input, Button, Checkbox } from 'antd';
import Info from '~/components/Banner/Info';
import Mgtop from '~/components/MgTop/Mgtop';
import Introduce from '~/components/Banner/Introduce';
import { methodGet, methodPost } from '~/Utils/Request';

export default function Login() {
    const navigate = useNavigate();
    const onFinish = async (values) => {
        try {
            const vl = {
                username: values.username,
                password: values.password,
            };
            console.log('form vl nekk:', vl);
            console.log('form vl:', values);
            const rs = await methodPost('/api/auth/signin', vl);
            console.log('rs', rs);
            if (rs.data.accessToken) {
                NotifySuccess('Đăng nhập thành công');
                localStorage.setItem('tokenType', rs.data.tokenType);
                localStorage.setItem('accessToken', rs.data.accessToken);
                console.log(rs.data.tokenType);
                console.log(rs.data.accessToken);
                // const User = await methodGet('/api/user/me');
                // console.log('user', User.data);
                // localStorage.setItem('User', JSON.stringify(User.data));
                setTimeout(() => {
                    // navigate('/'); // chuyển đến home
                    window.location.href = '/';
                }, 1000);
            } else {
                NotifyError(rs.data.message);
            }
        } catch (e) {
            NotifyError('Đăng nhập thất bại -> Kiểm tra lại :');
            console.log(e);
        }
    };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    return (
        <>
            {/* <Info title="Trang chủ " body="Trang chủ / Đăng Nhập"/> */}
            <Introduce title="Trang chủ " body="Trang chủ / Đăng Nhập" />
            <Mgtop />
            <div className="container">
                <h1>Chào mừng bạn đã quay trở lại </h1>
                <Form
                    name="basic"
                    labelCol={{
                        span: 6,
                    }}
                    wrapperCol={{
                        span: 10,
                    }}
                    initialValues={{
                        remember: true,
                        size: 'large',
                    }}
                    onFinish={onFinish}
                    onFinishFailed={onFinishFailed}
                    autoComplete="off"
                >
                    <Form.Item
                        label="Tài khoản"
                        name="username"
                        rules={[
                            {
                                required: true,
                                type: 'string',
                                min: 6,
                                max: 18,
                            },
                        ]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label="Mật khẩu"
                        name="password"
                        rules={[{ required: true, type: 'string', min: 6, max: 100 }]}
                    >
                        <Input.Password />
                    </Form.Item>

                    <Form.Item
                        name="remember"
                        valuePropName="checked"
                        wrapperCol={{
                            offset: 6,
                            span: 16,
                        }}
                    >
                        <Checkbox>Lưu Mật Khẩu ?</Checkbox>
                        <Form.Item name="link">
                            <Link className="login-form-register" to="/signUp">
                                Bạn chưa có tài khoản .Vui lòng Đăng ký !
                            </Link>
                            <Link style={{ marginLeft: '10%' }} className="login-form-register" to="/recovery">
                                Quên mật khẩu ?
                            </Link>
                        </Form.Item>
                    </Form.Item>

                    <Form.Item
                        wrapperCol={{
                            offset: 6,
                            span: 16,
                        }}
                    >
                        <Button type="danger" htmlType="submit">
                            Tiến hành đăng nhập
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        </>
    );
}
