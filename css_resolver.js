function checkOverrides() {
    let output = "";
    let styles = Array.from(document.styleSheets);
    let index = styles.findIndex((el) => el.href.includes("headers.css"));
    if (index === -1){
        throw "no headers.css file"
    }

    styles.forEach(el => el.disabled = true);

    styles[index].disabled = false;

    styles.splice(index, 1);

    let originColor = getH2Color();
    output = output.concat(`origin color + ${originColor} \n`);
    styles.forEach((cssStyle => {
        output = output.concat("Running " + cssStyle.href + "\n");
        cssStyle.disabled = false;
        if (originColor !== getH2Color()) {
            output = output.concat("color changed: " + getH2Color() + " from file " + cssStyle.href + "\n");
            cssStyle.disabled = true;
        }
    }));
    return output;
}


function getH2Color() {
    return getComputedStyle(document.getElementsByTagName("h2")[0])['color'];
}
