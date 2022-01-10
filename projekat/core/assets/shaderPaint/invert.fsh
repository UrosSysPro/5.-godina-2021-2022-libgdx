uniform sampler2D u_texture;
uniform vec2 start;
uniform vec2 end;
uniform vec2 res;

varying vec2 v_texCoords;

void main(){

    vec4 boja=texture2D(u_texture,v_texCoords);
    float x=v_texCoords.x*res.x;
    float y=v_texCoords.y*res.y;
    if((x>start.x&&x<end.x)&&(y>start.y&&y<end.y)){
        boja.x=float(1)-boja.x;
        boja.y=float(1)-boja.y;
        boja.z=float(1)-boja.z;

    }
    gl_FragColor=boja;
//    gl_FragColor=vec4(1,0,0,1);
}