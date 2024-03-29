import { createI18n } from "vue-i18n"

export function loadLanguages() {
    const context = import.meta.globEager("./languages/*.ts");

    const languages: AnyObject = {};

    let langs = Object.keys(context);
    for (let key of langs) {
        if (key === "./index.ts") return;
        let lang = context[key].lang;
        let name = key.replace(/(\.\/languages\/|\.ts)/g, '');
        languages[name] = lang
    }
    
    return languages
}

export const i18n = createI18n({
    // globalInjection: true,
    // legacy: false,
    locale: 'zh-cn',
    fallbackLocale: 'zh-cn',
    messages: loadLanguages()
})

export const i18nt = i18n.global.t

export function setLanguage(locale: string) {
    i18n.global.locale = locale
}