document.addEventListener('DOMContentLoaded', foo)

let output = ""

function foo() {
    let styles = Array.from(document.styleSheets)
    let index = styles.findIndex((el) => el.href.includes("headers.css"))
    styles.forEach(el => el.disabled = true)

    styles[index].disabled = false

    styles.splice(index, 1)

    output = output.concat(styles);

    let originColor = getH2Color()
    output = output.concat(originColor)
    styles.forEach((cssStyle => {
        output = output.concat("Running " + cssStyle.href);
        cssStyle.disabled = false;
        if (originColor !== getH2Color()) {
            output = output.concat("new color " + getH2Color() + "FROM " + cssStyle.href);
            cssStyle.disabled = true;
        }
    }));
    return output;
}


// console.log(styles[index])

function getH2Color() {
    return getComputedStyle(document.getElementsByTagName("h2")[0])['color']
}
