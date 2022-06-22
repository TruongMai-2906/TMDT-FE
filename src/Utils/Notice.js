import { message } from 'antd';

export const NotifySuccess = (msg) => {
    console.log('msg notify success:', msg);
    message.success({
        content: msg,
        duration: 3,
        style: {
            top: '16vh',
            left: '70%',
            position: 'absolute',
            zIndex: 10000,
        },
    });
};
export const NotifyError = (msg) => {
    console.log('msg notify fail:', msg);
    message.error({
        content: msg,
        duration: 3,
        style: {
            top: '16vh',
            left: '70%',
            position: 'absolute',
            zIndex: 10000,
        },
    });
};
