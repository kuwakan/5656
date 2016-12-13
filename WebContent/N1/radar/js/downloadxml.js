/**
*�񓯊��_�E�����[�h�Ɏg�p����XMLHttp�C���X�^���X��Ԃ��܂��B 
*���̃��\�b�h�͗�O���X���[���܂��񂪁A
*���炩�̗��R�Ńu���E�U��XmlHttp���T�|�[�g���Ă��Ȃ��ꍇ��NULL��Ԃ��܂��B
* @return {XMLHttpRequest | Null}
*/
function createXmlHttpRequest() {
 try {
   if (typeof ActiveXObject != 'undefined') {
     return new ActiveXObject('Microsoft.XMLHTTP');
   } else if (window["XMLHttpRequest"]) {
     return new XMLHttpRequest();
   }
 } catch (e) {
   changeStatus(e);
 }
 return null;
};

/**
*���̊֐��́AXMLHttpRequest��open / send�֐������b�v���܂��B
* URL���w�肵�āA�X�e�[�^�X�R�[�h��200�̏ꍇ�̓R�[���o�b�N���Ăяo���܂��B
* @param {String} url�擾����URL
* @param {Function} callback��x�Ăяo���֐����擾���܂��B
*/
function downloadUrl(url, callback) {
 var status = -1;
 var request = createXmlHttpRequest();
 if (!request) {
   return false;
 }

 request.onreadystatechange = function() {
   if (request.readyState == 4) {
     try {
       status = request.status;
     } catch (e) {
       // �ʏ�A���N�G�X�g��FF�Ń^�C���A�E�g�������Ƃ������܂��B
     }
     if ((status == 200) || (status == 0)) {
       callback(request.responseText, request.status);
       request.onreadystatechange = function() {};
     }
   }
 }
 request.open('GET', url, true);
 try {
   request.send(null);
 } catch (e) {
   changeStatus(e);
 }
};

/**
*�w�肳�ꂽXML���������͂��A��͂��ꂽ�h�L�������g��DOM�f�[�^�\���̂ɕԂ��܂��B
*���̊֐��́AXML��͂����̃u���E�U�ŃT�|�[�g����Ă��Ȃ��ꍇ�A���DOM�m�[�h��Ԃ��܂��B
* @param {string} str XML������
* @return {Element | Document} DOM
*/
function xmlParse(str) {
  if (typeof ActiveXObject != 'undefined' && typeof GetObject != 'undefined') {
    var doc = new ActiveXObject('Microsoft.XMLDOM');
    doc.loadXML(str);
    return doc;
  }

  if (typeof DOMParser != 'undefined') {
    return (new DOMParser()).parseFromString(str, 'text/xml');
  }

  return createElement('div', null);
}

/**
*�y�[�W��JavaScript�t�@�C����ǉ����܂��B
* @param {string} url
*/
function downloadScript(url) {
  var script = document.createElement('script');
  script.src = url;
  document.body.appendChild(script);
}