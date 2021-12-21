varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform int size;
uniform vec2 imgSize;

vec4 blur(sampler2D texture,vec2 pos, float size){
    vec4 suma=vec4(0);
    pos*=imgSize;
    for(float i=pos.x-size;i<=pos.x+size;i++){
        for(float j=pos.y-size;j<=pos.y+size;j++){
            vec4 color=texture2D(texture,vec2(i,j)/imgSize);
            suma+=color;
        }
    }
    float ukupno=size*float(2)+float(1);
    suma/=ukupno*ukupno;
    return suma;
}

void main(){
    vec4 color=blur(u_texture,v_texCoords,float(size));
    gl_FragColor=color;
}