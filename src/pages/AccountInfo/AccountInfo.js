import { Form, Input, InputNumber, Button, Select, Col, Row } from 'antd';
import React, { useEffect, useState } from 'react';
// import 'antd/dist/antd.min.css';
import Introduce from '~/components/Banner/Introduce';
import Mgtop from '~/components/MgTop/Mgtop';
import { Link } from 'react-router-dom';
import { methodGet, methodPost } from '~/Utils/Request';
import axios from 'axios';
import { NotifyError, NotifySuccess } from '~/Utils/Notice';
const { Option } = Select;
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
const layout = {
    labelCol: {
        span: 4,
    },
    wrapperCol: {
        span: 16,
    },
};
export default function AccountInfo() {
    const onFinish = async (values) => {
        console.log(values);
        const kq = await methodPost('/api/user/update', values).catch((e) => {
            NotifyError('Lỗi kiểm tra lại');
            console.log('lỗi call api update', e);
        });
        if (kq.data.id) {
            NotifySuccess('Đã cập nhật thành công');
            form.setFieldsValue(values);
        } else {
            NotifyError('Cập nhật thất bại =>>Kiểm tra lại');
        }
        console.log('kết quả update', kq);
    };

    const [form] = Form.useForm();

    useEffect(() => {
        const callAPi = async () => {
            const rs = await methodGet('/api/user/me').catch((e) => {
                console.log('lỗi get info acc:' + e);
            });
            form.setFieldsValue({
                name: rs.data.name,
                username: rs.data.username,
                email: rs.data.email,
                phone: rs.data.phone,
                address: rs.data.address,
                password: rs.data.password,
                gender: rs.data.gender,
            });

            console.log('Api info call ra:', rs);
        };

        callAPi();
    }, []);
    const onGenderChange = (e) => {};
    return (
        <div className="row">
            <Mgtop />
            <Introduce title="Thông tin tài khoản" body="Trang chủ / Info" />
            <div className="container">
                <Form
                    form={form}
                    {...layout}
                    // initialValues={initvalue}
                    name="nest-messages"
                    onFinish={onFinish}
                    validateMessages={validateMessages}
                >
                    <div className="edit_avatar">Thông tin tài khoản </div>
                    <Form.Item
                        name="name"
                        label="Họ và tên"
                        wrapperCol={{
                            span: 7,
                        }}
                        rules={[
                            {
                                required: true,
                            },
                        ]}
                    >
                        <Input />
                    </Form.Item>
                    <Form.Item
                        name="username"
                        label="Tên Tài khoản "
                        wrapperCol={{
                            span: 7,
                        }}
                        rules={[
                            {
                                required: true,
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
                        label="E-mail"
                        rules={[
                            {
                                type: 'email',
                                required: true,
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
                                <Select placeholder="Vui lòng chọn giới tính" onChange={onGenderChange} allowClear>
                                    <Option value="Nam">Nam</Option>
                                    <Option value="Nữ">Nữ</Option>
                                    <Option value="Khác">Khác</Option>
                                </Select>
                            </Form.Item>
                        </Col>
                    </Row>
                    <Form.Item name="address" label="Địa chỉ">
                        <Input.TextArea />
                    </Form.Item>
                    {/* <Form.Item name="password" label="Mật khẩu" rules={[{ required: true, min: 6, max: 18 }]}>
                        <Input.Password />
                    </Form.Item> */}
                    <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 8 }}>
                        <Button type="danger" htmlType="submit">
                            Lưu lại
                        </Button>
                        <Button type="">
                            <Link to="/">Quay lại trang chủ</Link>
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        </div>
    );
}
