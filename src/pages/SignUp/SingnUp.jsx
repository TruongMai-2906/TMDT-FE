import { Form, Input, InputNumber, Button, Select, Col, Row } from 'antd';
import React from 'react';
import { useNavigate } from 'react-router-dom';
// import 'antd/dist/antd.min.css';

import Introduce from '~/components/Banner/Introduce';
import { NotifyError, NotifySuccess } from '~/Utils/Notice';
import { methodPost } from '~/Utils/Request';
const layout = {
    labelCol: {
        span: 4,
    },
    wrapperCol: {
        span: 16,
    },
};
const validateMessages = {
    required: '${label} là bắt buộc!',
    types: {
        email: '${label} ko phải định dạng email!',
        number: '${label} is not a valid number!',
    },
    number: {
        range: '${label} phải nằm trong khoảng ${min} và ${max}',
    },
};
const { Option } = Select;
export default function SingnUp() {
    const navigate = useNavigate();
    const onFinish = async (values) => {
        console.log(values);
        try {
            delete values.confirm;
            delete values.address;
            console.log('giá trị form:', values);
            const data = await methodPost('/api/auth/signup', values);
            console.log('data ne,', data.data);
            if (data.data.success) {
                NotifySuccess(data.data.message);
                setTimeout(() => {
                    navigate('/login');
                }, 1500);
            } else {
                NotifyError(data.data.message);
            }
        } catch (e) {
            console.log('lỗi signup:' + e);
        }
    };
    const onGenderChange = (e) => {};
    return (
        <div>
            <Introduce title="Đăng ký" body="Trang chủ / Đăng ký" />
            <div className="resgiter">
                <div className="container">
                    <h1 className="">Đăng ký tài khoản</h1>
                    <div className="">
                        <Form {...layout} name="nest-messages" onFinish={onFinish} validateMessages={validateMessages}>
                            <Form.Item
                                name="name"
                                hasFeedback
                                label="Họ và tên"
                                wrapperCol={{
                                    span: 7,
                                }}
                                rules={[
                                    {
                                        required: true,
                                    },
                                    {
                                        max: 40,
                                    },
                                ]}
                            >
                                <Input />
                            </Form.Item>
                            <Form.Item
                                name="username"
                                label="Tài khoản đăng nhập"
                                hasFeedback
                                wrapperCol={{
                                    span: 7,
                                }}
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
                                name="email"
                                wrapperCol={{
                                    span: 10,
                                }}
                                hasFeedback
                                label="Email"
                                rules={[
                                    {
                                        type: 'email',
                                        required: true,
                                        max: 40,
                                    },
                                ]}
                            >
                                <Input />
                            </Form.Item>
                            <Row gutter={15}>
                                <Col span={12} push={2}>
                                    <Form.Item
                                        name="phone"
                                        label="Sdt ( Phone )"
                                        hasFeedback
                                        rules={[
                                            {
                                                required: true,
                                                message: 'Số điện thoại bắt buộc và từ 9-11 số',
                                                min: 9,
                                                max: 12,
                                            },
                                        ]}
                                    >
                                        <Input type="number" />
                                    </Form.Item>
                                </Col>
                                <Col span={8}>
                                    <Form.Item
                                        name="gender"
                                        // label="Giới tính"
                                        rules={[
                                            {
                                                required: true,
                                            },
                                        ]}
                                        hasFeedback
                                    >
                                        <Select
                                            placeholder="Vui lòng chọn giới tính"
                                            onChange={onGenderChange}
                                            allowClear
                                        >
                                            <Option value="Nam">Nam</Option>
                                            <Option value="Nữ">Nữ</Option>
                                            <Option value="Khác">Khác</Option>
                                        </Select>
                                    </Form.Item>
                                </Col>
                            </Row>

                            <Form.Item name="address" label="Địa chỉ" hasFeedback>
                                <Input.TextArea />
                            </Form.Item>
                            <Form.Item
                                labelCol={{
                                    span: 4,
                                }}
                                wrapperCol={{
                                    span: 8,
                                }}
                                hasFeedback
                                name="password"
                                label="Mật khẩu"
                                rules={[{ required: true, type: 'string', min: 6, max: 99 }]}
                            >
                                <Input.Password />
                            </Form.Item>
                            <Form.Item
                                name="confirm"
                                label="Xác nhận Password"
                                dependencies={['password']}
                                hasFeedback
                                // hasFeedback
                                labelCol={{
                                    span: 4,
                                }}
                                wrapperCol={{
                                    span: 8,
                                }}
                                rules={[
                                    {
                                        required: true,
                                        message: 'Please confirm your password!',
                                    },
                                    ({ getFieldValue }) => ({
                                        validator(_, value) {
                                            if (!value || getFieldValue('password') === value) {
                                                return Promise.resolve();
                                            }

                                            return Promise.reject(
                                                new Error('The two passwords that you entered do not match!'),
                                            );
                                        },
                                    }),
                                ]}
                            >
                                <Input.Password />
                            </Form.Item>
                            <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 8 }}>
                                <Button type="primary" htmlType="submit">
                                    Đăng ký
                                </Button>
                            </Form.Item>
                        </Form>
                    </div>
                </div>
            </div>
        </div>
    );
}
