
varying vec2 v_texCoords;
uniform sampler2D u_texture;

void main(){
    vec4 color=texture2D(u_texture,v_texCoords);
    float p=color.r;
    color.r=color.g;
    color.g=p;
    gl_FragColor=color;
}