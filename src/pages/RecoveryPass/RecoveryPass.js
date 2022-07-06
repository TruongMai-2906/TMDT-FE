import { Form } from 'antd';
import React from 'react';
import Introduce from '~/components/Banner/Introduce';
import Mgtop from '~/components/MgTop/Mgtop';
import { Button, Input } from 'antd';
import { methodGet } from '~/Utils/Request';
import { NotifyError, NotifySuccess } from '~/Utils/Notice';
import { useNavigate } from 'react-router-dom';
import Footer from '~/components/Footer/Footer';

const RecoveryPass = () => {
    const navigate = useNavigate();
    const onFinish = async (values) => {
        const { Email } = values;
        const rs = await methodGet(`/api/auth/forgotpassword/${Email}`);
        if (rs?.data.success) {
            NotifySuccess(rs?.data?.message);
            navigate('/login');
        } else {
            NotifyError(rs?.data?.message);
        }
    };

    return (
        <>
            <Introduce title="Trang chủ " body="Trang chủ / Đăng Nhập" />
            <Mgtop />
            <div className="container">
                <Form
                    name="basic"
                    labelCol={{
                        span: 4,
                    }}
                    wrapperCol={{
                        span: 12,
                    }}
                    initialValues={{
                        remember: true,
                    }}
                    onFinish={onFinish}
                    autoComplete="off"
                >
                    <h1>Vui lòng nhập vào Email đăng kí tài khoản ! </h1>
                    <Form.Item
                        label="Email"
                        name="Email"
                        rules={[
                            {
                                required: true,
                                message: 'Vui lòng điền email!',
                            },
                            {
                                type: 'email',
                                message: 'Vui lòng điền email!',
                            },
                        ]}
                    >
                        <Input />
                    </Form.Item>
                    <Form.Item
                        wrapperCol={{
                            offset: 8,
                            span: 16,
                        }}
                    >
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Form>
            </div>
            <Footer />
        </>
    );
};

export default RecoveryPass;
